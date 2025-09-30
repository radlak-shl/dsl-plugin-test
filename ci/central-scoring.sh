#!/bin/bash
set -e

case "$1" in
  prebuild)
    echo "[INFO] Running prebuild for central-scoring"
    # add your prebuild steps here
    ;;
  postbuild)
    echo "[INFO] Running postbuild for central-scoring"
    # add your postbuild steps here
    ;;
  *)
    echo "[ERROR] Unknown command: $1"
    exit 1
    ;;
esac
