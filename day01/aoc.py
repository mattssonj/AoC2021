import os


def solutionPart1(lines):
    increases = 0
    for p in zip(lines, lines[1:]):
        if p[0] < p[1]:
            increases += 1
    return increases


def solutionPart2(lines):
    return solutionPart1(list(map(sum, zip(lines, lines[1:], lines[2:]))))


part = os.environ.get('part')
inputLines = list(int(line) for line in open("input.txt", "r").readlines())

if part == 'part2':
    print(solutionPart2(inputLines))
else:
    print(solutionPart1(inputLines))
