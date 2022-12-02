with open(r'input.txt', 'r') as file:
    directions = file.read().splitlines()


def part1() -> int:
    forward = 0
    depth = 0
    for direction in directions:
        cmd, unit = direction.split()
        unit = int(unit)
        if cmd == 'up':
            depth -= unit
        elif cmd == 'down':
            depth += unit
        forward = forward + unit if cmd == 'forward' else forward
    return forward*depth


def part2() -> int:
    forward = 0
    depth = 0
    aim = 0
    for direction in directions:
        cmd, unit = direction.split()
        unit = int(unit)
        if cmd == 'forward':
            forward += unit
            depth += aim*unit
        elif cmd == 'up':
            aim -= unit
        elif cmd == 'down':
            aim += unit
    return forward*depth


print(part1())
print(part2())
