#!/bin/sh

# Replace environment variables in env-config.template.js
envsubst < /usr/share/nginx/html/env-config.template.js > /usr/share/nginx/html/env-config.js

# Execute the CMD (start Nginx)
exec "$@"
