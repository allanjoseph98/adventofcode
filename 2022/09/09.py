with open('input.txt', 'r') as file:
    directions = file.read().splitlines()

rope: list[tuple[int, int]] = [(0, 0)]*10
head_locations: list[tuple[int, int]] = []
tail_locations: list[tuple[int, int]] = []


def tail_move(knot1: tuple[int, int], knot2: tuple[int, int]) -> tuple[int, int]:
    x1, y1 = knot1
    x2, y2 = knot2
    one_behind = lambda x1,x2: x1-1 if x1>x2 else x1+1
    if (abs(y2-y1) >= 2 and x1!=x2): # TODO optimise
        x2 += 1 if x1>x2 else -1
        y2 = one_behind(y1,y2)
    elif (abs(x2-x1) >= 2 and y1!=y2):
        y2 += 1 if y1>y2 else -1
        x2 = one_behind(x1,x2)
    elif abs(x2-x1) >= 2:
        x2 = one_behind(x1,x2)
    elif abs(y2-y1) >= 2:
        y2 = one_behind(y1,y2)
    return x2, y2


def move(direction: str) -> None:
    x1, y1 = rope[0]
    if direction == 'L':
        x1 -= 1
    elif direction == 'R':
        x1 += 1
    elif direction == 'U':
        y1 += 1
    elif direction == 'D':
        y1 -= 1
    rope[0] = x1,y1
    i=1
    while i!=len(rope):
        rope[i] = tail_move(rope[i-1], rope[i])
        i+=1


for x in directions:
    direction, unit = x.split()
    for digit in range(int(unit)):
        move(direction)
        head, tail = rope[0],rope[-1]
        head_locations.append(head)
        tail_locations.append(tail)

print(len(set(tail_locations)))
