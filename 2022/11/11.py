from math import prod

with open('input.txt', 'r') as file:
    directions = file.read().split('\n\n')
        

class Monkey:
    def __init__(self, items: list[int], operation: str, test: int, throw_to: dict[bool, int]) -> None:
        self.items: list[int] = items.copy()
        self.operation = operation
        self.test = test
        self.throw_to = throw_to.copy()
        self.visited: int = 0


def parse() -> list[Monkey]:
    monkeys: list[Monkey] = []
    for property in directions:
        items: list[int] = []
        operation: str = ''
        test: int = 0
        throw_to: dict[bool, int] = {}
        for line in property.splitlines()[1:]:
            if 'Starting items' in line:
                evaled:tuple[int] | int = eval(line.split(':')[-1])
                if type(evaled) is int:
                    items = [evaled]
                elif type(evaled) is tuple:
                    items = list(evaled)
            elif 'Operation' in line:
                operation = (line.split(':')[-1]).split('=')[-1].strip()
            elif 'Test' in line:
                test = int(line.split()[-1])
            elif 'If true' in line:
                throw_to.update({True: int(line.split()[-1])})
            elif 'If false' in line:
                throw_to.update({False: int(line.split()[-1])})
        monkeys.append(Monkey(items, operation, test, throw_to))
    return monkeys


def throw(monkeys: list[Monkey], stop: int, worry: bool) -> list[Monkey]: #TODO un-nest
    kn = prod(set(monkey.test for monkey in monkeys))
    for i in range(stop):
        for monkey in monkeys:
            to_get_bool: bool = False
            for item in monkey.items:
                operand1 = monkey.operation.split()[0]
                operand2 = monkey.operation.split()[-1]
                operand1 = int(operand1) if operand1.isdigit() else item
                operand2 = int(operand2) if operand2.isdigit() else item
                if '*' in monkey.operation:
                    new = operand1 * operand2
                else:
                    new = operand1 + operand2
                new = new // 3 if worry else new % kn
                to_get_bool = new % monkey.test == 0
                throw_to = monkey.throw_to.get(to_get_bool, 0)
                monkeys[throw_to].items.append(new)
                monkey.visited += 1
            monkey.items.clear()
    return monkeys


for i in range(2):
    monkeys = parse()
    value_counts: dict[int, int] = {}
    if i == 0:
        monkeys = throw(monkeys, 20, True)
    else:
        monkeys = throw(monkeys, 10000, False)
    for j, monkey in enumerate(monkeys):
        value_counts.update({j: monkey.visited})
    top1, top2 = (sorted(value_counts.items(), key=lambda x: x[1]))[-2:]
    print(f'Part{i+1}: {top1[1]*top2[1]}')
