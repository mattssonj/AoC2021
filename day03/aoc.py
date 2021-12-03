import os

toInt = lambda binary: int("".join(str(i) for i in binary), 2)


def numberOfOnesPerLine(lines):
    most = {}
    for line in lines:
        for i, n in enumerate(line):
            most.update({i: most.get(i, 0) + int(n)})
    return most


def solutionPart1(lines):
    numberOfOnes = numberOfOnesPerLine(lines)
    binary = list(map(lambda item: 1 if (len(lines) - item[1]) < item[1] else 0, numberOfOnes.items()))
    inverted = list(map(lambda x: x ^ 1, binary))
    return toInt(binary) * toInt(inverted)


def getLifeRating(lines, index, co2Value):
    if len(lines) <= 1:
        return lines
    numberOfOnesOnCurrentIndex = numberOfOnesPerLine(lines).get(index)
    mostOf = 1 if (len(lines) - numberOfOnesOnCurrentIndex) <= numberOfOnesOnCurrentIndex else 0
    return getLifeRating(list(filter(lambda line: int(line[index]) == (mostOf if not co2Value else mostOf ^ 1), lines)), index+1, co2Value)


def solutionPart2(lines):
    oxygen = getLifeRating(lines, 0, False)
    co2 = getLifeRating(lines, 0, True)
    return toInt(oxygen) * toInt(co2)


inputLines = list(map(lambda line: line.replace("\n", ""), open("input.txt", "r").readlines()))
print(solutionPart2(inputLines)) if os.environ.get('part') == 'part2' else print(solutionPart1(inputLines))
