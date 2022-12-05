import re
from copy import deepcopy

with open('input.txt', 'r') as file:
    setup, moves = list(
        map(lambda x: x.split('\n'), file.read().split('\n\n')))

MOVE_REGEX = re.compile(r'move\s(\d+)\sfrom\s(\d+)\sto\s(\d+)')

crates = [list(map(lambda x: x[i], setup[-2::-1]))
          for i in range(1, min(map(len, setup[-2::-1])), 4)]

crates = [list(filter(lambda x: x != ' ', stack)) for stack in crates]
crates2 = deepcopy(crates)


def move_op(crates: list[list[str]], crates2: list[list[str]], qty: int, source: int, dest: int):
    crates[dest] += crates[source][-qty:][::-1]
    crates2[dest] += crates2[source][-qty:]
    crates[source] = crates[source][:-qty]
    crates2[source] = crates2[source][:-qty]


commands: list[tuple[int, int, int]] = []
for move in moves:
    match = MOVE_REGEX.match(move)
    if match:
        commands.append(
            tuple(map(int, (match.group(1), match.group(2), match.group(3)))))

for how_many, source, dest in commands:
    move_op(crates, crates2, how_many, source-1, dest-1)

print(''.join((map(lambda x: x[-1], crates))))
print(''.join((map(lambda x: x[-1], crates2))))
