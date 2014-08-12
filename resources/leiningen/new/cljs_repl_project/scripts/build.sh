#!/usr/bin/env bash

lein do \
     cljx once, \
     cljsbuild clean, \
     cljsbuild once
