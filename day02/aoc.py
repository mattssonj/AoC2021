import os

from functools import reduce

def move(current, movement):
    direction, value = movement.split()
    x, y = current
    if direction == 'forward':
        x += int(value)
    if direction == 'down':
        y += int(value)
    if direction == 'up':
        y -= int(value)
    return x, y

def moveWithAim(current, movement):
    a = movement.split()
    x, y, aim = current
    if a[0] == 'forward':
        x += int(a[1])
        y += (aim * int(a[1]))
    if a[0] == 'down':
        aim += int(a[1])
    if a[0] == 'up':
        aim -= int(a[1])
    return x, y, aim

def solutionPart1(lines):
    pos = reduce(move, lines, (0, 0))
    return pos[0] * pos[1]

def solutionPart2(lines):
    pos = reduce(moveWithAim, lines, (0, 0, 0))
    return pos[0] * pos[1]

part = os.environ.get('part')
inputLines = open("input.txt", "r").readlines()

if part == 'part2':
    print(solutionPart2(inputLines))
else:
    print(solutionPart1(inputLines))
