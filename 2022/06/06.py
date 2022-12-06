with open('input.txt', 'r') as file:
    stream = file.read()

part1 = 0
part2 = 0
for i in range(3, len(stream)):
    packets = [stream[j] for j in range(i-3, i+1)]
    message = [stream[j] for j in range(i-13, i+1)]
    part1 = i+1 if (len(set(packets)) == 4 and not part1) else part1
    part2 = i+1 if (len(set(message)) == 14 and not part2) else part2
    if part2:
        break

print(part1, part2)
