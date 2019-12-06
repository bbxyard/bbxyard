package main

import (
	"fmt"
	"os"
)

type Maze [][]int
type Point struct {
	i, j int
}

func readMaze(filename string) Maze {
	file, err := os.Open(filename)
	if err != nil {
		panic(err)
	}

	row, col := 0, 0
	fmt.Fscanf(file, "%d %d", &row, &col)

	maze := make(Maze, row)
	for i := range maze {
		maze[i] = make([]int, col)
		for j := range maze[i] {
			fmt.Fscanf(file, "%d", &maze[i][j])
		}
	}
	return maze
}

var directs = [4]Point{
	{-1, 0}, {0, -1}, {1, 0}, {0, 1},
}

func (p Point) add(r Point) Point {
	return Point{p.i + r.i, p.j + r.j}
}

func (p Point) at(grid [][]int) (int, bool) {
	cond := p.i < 0 || p.i >= len(grid) || p.j < 0 || p.j >= len(grid[p.i])
	if cond {
		return 0, false
	} else {
		return grid[p.i][p.j], true
	}
}

func walk(maze Maze, begin, end Point) Maze {
	steps := make(Maze, len(maze))
	for i := range steps {
		steps[i] = make([]int, len(maze[i]))
	}

	Q := []Point{begin}
	for len(Q) > 0 {
		cur := Q[0]
		Q = Q[1:]
		if cur == end {
			break
		}

		for _, dir := range directs {
			next := cur.add(dir)
			val, ok := next.at(maze)
			if !ok || val == 1 { // wall
				continue
			}
			val, ok = next.at(steps)
			if !ok || val != 0 { // traversed
				continue
			}
			if next == begin {
				continue
			}

			curSteps, _ := cur.at(steps)
			steps[next.i][next.j] = curSteps + 1

			Q = append(Q, next)
		}
	}
	return steps
}

func main() {
	maze := readMaze("alg/maze/maze.in")
	steps := walk(maze, Point{0, 0}, Point{len(maze) - 1, len(maze[0]) - 1})
	for _, row := range steps {
		for _, val := range row {
			fmt.Printf("%3d", val)
		}
		fmt.Println()
	}
}
