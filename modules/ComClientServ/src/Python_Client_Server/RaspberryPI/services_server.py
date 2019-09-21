#!/usr/bin/env python3

import fileinput
import os
import re
import subprocess
import sys
import tempfile
import textwrap
import traceback
import socket               # Import socket module
from gpiozero import PWMOutputDevice
from time import sleep
pwm = PWMOutputDevice(4)

import aiy.audio  # noqa
from aiy._drivers._hat import get_aiy_device_name

AIY_PROJECTS_DIR = os.path.dirname(os.path.dirname(__file__))

CARDS_PATH = '/proc/asound/cards'
CARDS_ID = {
    "Voice Hat": "googlevoicehat",
    "Voice Bonnet": "aiy-voicebonnet",
}

STOP_DELAY = 1.0

TEST_SOUND_PATH = '/usr/share/sounds/alsa/Front_Center.wav'

RECORD_DURATION_SECONDS = 3


def get_sound_cards():
    """Read a dictionary of ALSA cards from /proc, indexed by number."""
    cards = {}

    with open(CARDS_PATH) as f:  # pylint: disable=invalid-name
        for line in f.read().splitlines():
            try:
                index = int(line.strip().split()[0])
            except (IndexError, ValueError):
                continue

            cards[index] = line

    return cards


def ask(prompt):
    """Get a yes or no answer from the user."""
    ans = input(prompt + ' (y/n) ')

    while not ans or ans[0].lower() not in 'yn':
        ans = input('Please enter y or n: ')

    return ans[0].lower() == 'y'


def check_voicehat_present():
    """Check that the voiceHAT audio driver is present."""
    card_id = CARDS_ID[get_aiy_device_name()]
    return any(card_id in card for card in get_sound_cards().values())


def check_voicehat_is_first_card():
    """Check that the voiceHAT is the first card on the system."""
    cards = get_sound_cards()
    card_id = CARDS_ID[get_aiy_device_name()]
    return 0 in cards and card_id in cards[0]


def check_asoundrc_is_not_bad():
    """Check that ~/.asoundrc is absent or has the AIY config."""
    asoundrc = os.path.expanduser('~/.asoundrc')
    if not os.path.exists(asoundrc):
        return True

    with open(os.path.join(AIY_PROJECTS_DIR, 'scripts', 'asound.conf')) as f:
        wanted_contents = f.read()
    with open(asoundrc) as f:
        contents = f.read()

    return contents == wanted_contents


def check_speaker_works():
    """Check the speaker makes a sound."""
    print('Playing a test sound...')
    aiy.audio.play_wave(TEST_SOUND_PATH)

    return ask('Did you hear the test sound?')


def do_checks():
    """Run all audio checks and print status."""
    if not check_voicehat_present():
        print(textwrap.fill(
            """Failed to find the voiceHAT soundcard. Refer to HACKING.md for
how to setup the voiceHAT driver: https://git.io/v99yK"""))
        return

    if not check_voicehat_is_first_card():
        print(textwrap.fill(
            """The voiceHAT not the first sound device, so the voice recognizer
may be unable to find it. Please try removing other sound drivers."""))
        return

    print('The audio seems to be working.')


def enable_audio_driver():
    print("Enabling audio driver for VoiceKit.")
    configure_driver = os.path.join(AIY_PROJECTS_DIR, 'scripts', 'configure-driver.sh')
    subprocess.check_call(['sudo', configure_driver])


def main():
    if get_aiy_device_name() == 'Voice Hat':
        enable_audio_driver()
    do_checks()
    soc = socket.socket()         # Create a socket object
    host = "192.168.137.233" # Get local machine name
    port = 65009                # Reserve a port for your service.
    soc.bind((host, port))       # Bind to the port
    soc.listen(5)                 # Now wait for client connection.
    #soc.settimeout(0.5)
    to_send = str("")


    while True:

        try:
            conn, addr = soc.accept()     # Establish connection with client.
            print ("Got connection from",addr)
            msg = conn.recv(1024)
            text = str(msg)

            motor = str("motor\n")
            sound = str("sound\n")
            stop = str("stop\n")
            #print (text)
            #print("Hello")

            #conn.sendall(to_send)

            #print(id(text))
            #print(id("Hello"))
            #conn.sendall("Motor_running")
            if ( text == str("Hello\n") ):
                to_send = str("Hii everyone")
                print("Hii everyone")
                aiy.audio.say("Hii everyone")
                conn.sendall(to_send)
            elif( text == motor ):
                to_send = str("Motor is running")
                print("Motor")
                pwm.on()
                conn.sendall(to_send)
                aiy.audio.say("Motor is running")
            elif( text == sound ):
                print("Sound")
                conn.sendall(sound)
            elif( text == stop ):
                to_send = str("Motor stopped")
                pwm.off()
                conn.sendall(to_send)
                aiy.audio.say("Motor stopped")
            else:
                print("Go away\n\n\n ")
                #conn.sendall("Go away")
                aiy.audio.say("Go away")

            conn.close()
        except KeyboardInterrupt:
            print ("BYE")
            soc.close()



if __name__ == '__main__':
    try:
        main()
        input('Press Enter to close...')
    except Exception:  # pylint: disable=W0703
        traceback.print_exc()
        input('Press Enter to close...')
