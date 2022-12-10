with open('input.txt', 'r') as file:
    ops = file.read().splitlines()

x: int = 1
cycle: int = 0
cycling_till: int = 0
signal_strengths: list[int] = []
crt: list[str] = [''] * 240

for op in ops:
    increment = 0
    if op == 'noop':
        cycling_till = cycle + 1
    else:
        increment = int(op.split()[-1])
        cycling_till = cycle + 2
    while cycling_till > cycle:
        if (cycle+1-20) % 40 == 0:
            signal_strengths.append((cycle+1)*x)
        crt[cycle] = '#' if cycle % 40 in [x-1, x, x+1] else '.'
        cycle += 1
    if increment:
        x += increment
print(sum(signal_strengths))
for i in range(6):
    print(''.join(crt[40*i:40+40*i]))
