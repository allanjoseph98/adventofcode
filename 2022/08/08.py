from math import prod


with open('input.txt', 'r') as file:
    trees = [[int(tree) for tree in line] for line in file.read().splitlines()]


def dimensional_visible(tree: int, row: list[int]) -> bool:
    return False if any(x >= tree for x in row) else True


def scenic(tree: int, direction: list[int]) -> int:
    score = 0
    for x in direction:
        if x < tree:
            score += 1
        else:
            score += 1
            break
    return score


def part1_and_2(i: int, j: int) -> tuple[bool, int]:
    up = [x[j] for x in trees[i-1::-1]]
    down = [x[j] for x in trees[i+1:]]
    left = trees[i][j-1::-1]
    right = trees[i][j+1:]
    tree = trees[i][j]
    up_bool = dimensional_visible(tree, up)
    down_bool = dimensional_visible(tree, down)
    left_bool = dimensional_visible(tree, left)
    right_bool = dimensional_visible(tree, right)
    score = prod([scenic(tree, up), scenic(tree, down),
                  scenic(tree, left), scenic(tree, right)])
    visibility = True if any(
        [up_bool, down_bool, left_bool, right_bool]) else False
    return (visibility, score)


visible = 2*(len(trees)+len(trees[0]))-4
scenic_scores: list[list[int]] = []
for i in range(1, len(trees)-1):
    row: list[int] = []
    for j in range(1, len(trees[0])-1):
        visiblity, score = part1_and_2(i, j)
        visible += 1 if visiblity else 0
        row.append(score)
    scenic_scores.append(row)
print(visible, max(map(max, scenic_scores)))
