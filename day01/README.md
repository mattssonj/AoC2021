# Python

This project adds the source to a minimal Python image.

## How to build
```bash
docker build -t aoc01 . 
```

## How to run
The environment variable "part" specifies which part of the solution to run. Please adjust your code accordingly (see example in aoc.py).
```bash
docker run -e part=part1 aoc01
```