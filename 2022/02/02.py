from typing import Tuple, List

with open(r'input.txt', 'r') as file:
    strat = file.read().splitlines()

SCORES = {'X': 1, 'Y': 2, 'Z': 3, 'A': 1, 'B': 2, 'C': 3}

LOSS: List[Tuple[str, str]] = [('A', 'Z'), ('B', 'X'), ('C', 'Y')]


def result1(opp_plays: str, you_play: str) -> int:
    if SCORES.get(opp_plays) == SCORES.get(you_play):
        return SCORES[you_play] + 3
    return SCORES[you_play] if (opp_plays, you_play) in LOSS else SCORES[you_play] + 6


def result2(opp_plays: str, result: str) -> int:
    if result == 'Y':
        return SCORES.get(opp_plays, 0) + 3
    else:
        for x, y in LOSS:
            if opp_plays == x:
                if result == 'X':
                    return SCORES.get(y, 0)
            elif SCORES.get(opp_plays) != SCORES.get(y) and result == 'Z':
                return SCORES.get(y, 0) + 6
        return -1


print(sum([result1(round[0], round[-1]) for round in strat]))
print(sum([result2(round[0], round[-1]) for round in strat]))
