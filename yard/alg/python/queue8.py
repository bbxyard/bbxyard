#!/usr/bin/env python3
#* queen problem with recurison
BOARD_SIZE = 4


def under_attack(col, queens):
    left = right = col
    for r, c in reversed(queens):
        #左右有冲突的位置的列号
        left, right = left - 1, right + 1
        # print (r, [c], (left, col, right), queens)

        if c in (left, col, right):
            return True
    return False


def solve(n):
    if n == 0:
        return [[]]

    smaller_solutions = solve(n - 1)
    print("smaller_solutions: ", len(smaller_solutions), smaller_solutions)

    return [
        solution + [(n, i + 1)] for i in range(BOARD_SIZE)
        # for solution in smaller_solutions if not under_attack(i + 1, solution)
        for solution in smaller_solutions
    ]


for answer in solve(BOARD_SIZE):
    print(answer)
