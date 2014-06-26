Frisk
=====
Software that will analyse a song, rename and fill missing metadata.

This was a project in IntelliJ. Please import it inside IntelliJ to try.
Frisk assumes that the song name is "Track01.mp3". (You can change Frisk.java otherwise).
The song must be present in the project folder.
It will give the Title, Artist and Length of the track.
It doesn't fill the metadata just yet.

How it works
============

fpcalc.exe is code written by  Lukáš Lalinský. It can be used to export the fingerprint of the 
track.

The track is passed as a command line argument to fpcalc.exe. The output is parsed for the fingerprint
and duration details.

From these, a request is sent to https://acoustid.org/ 
The song 'id' is taken from the response. This is used to send another request.

The response contains the TrackInfo, which is then extracted and displayed.


Drawbacks
=========

The software's is limited to MusicBrainz database. 
Fetches only artist and track name, as of now.










