from functools import reduce

with open('input.txt', 'r') as file:
    rucksacks = file.read().splitlines()


def priority(x: str) -> int: return ord(x)-64+26 if ord(x) <= 95 else ord(x)-96


def mid(x: str) -> int: return int(len(x)/2)


def cal(group) -> int:
    return priority(reduce(set.intersection, map(set, group)).pop())


part1 = map(cal, tuple((rucksack[:mid(rucksack)], rucksack[mid(rucksack):]) for rucksack in rucksacks))
part2 = map(cal, tuple((rucksacks[i], rucksacks[i+1], rucksacks[i+2]) for i in range(0,len(rucksacks),3)))

print(sum(part1))
print(sum(part2))
