with open('input.txt', 'r') as file:
    pairs = file.read().splitlines()


def range_to_ints(range: str): return tuple(map(int, range.split('-')))


def ranges(elf1: str, elf2: str): return (
    *range_to_ints(elf1), *range_to_ints(elf2))


part1 = []
part2 = []

for pair in pairs:
    elf1, elf2 = pair.split(',')
    x1, x2, y1, y2 = ranges(elf1, elf2)
    part1.append(True if (x1 <= y1 and x2 >= y2)
                 or (x1 >= y1 and x2 <= y2) else False)
    part2.append(True if (x1 <= y1 and x2 >= y1)
                 or (x1 >= y1 and x1 <= y2) else False)

print(sum(part1), sum(part2))
