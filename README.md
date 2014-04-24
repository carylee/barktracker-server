# barktracker-server

This is the server portion of a client-server-client application for monitoring the barking habits of a dog. We have a dog with separation anxiety issues, so we like to monitor his barking while we're away so we can track his improvement as we train him (and know how bad things are for our neighbors).

See also barktracker-client. Eventually this app will also have a web client (and possibly mobile client) for viewing the barking habits and trends.

This portion of the app receives http requests from the barktracker-client as the dog barks.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2014 Cary Lee
