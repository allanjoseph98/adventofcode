from typing import List


with open(r'input.txt', 'r') as file:
    input = file.readlines()


class Elf(object):
    def __init__(self, id: int, foodies: List[int]):
        self.id = id
        self.foodies = foodies.copy()


elves: List[Elf] = []
food: List[int] = []
elf_id = 1


for line in input:
    if line != '\n':
        calories = int(line.strip())
        food.append(calories)
    else:
        elf = Elf(elf_id, food)
        elves.append(elf)
        elf_id += 1
        food.clear()

print(max([sum(elf.foodies) for elf in elves]))
print(sum(sorted([sum(elf.foodies) for elf in elves], reverse=True)[:3]))
