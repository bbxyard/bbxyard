package main

func displayPerm(arr []int) {
	for x := range arr {
		print(x, "")
	}
	println()
}

func swap(lhs, rhs int) (int, int) {
	lhs, rhs = rhs, lhs
	return lhs, rhs
}

// func swapElemInArr(arr *[]int, i, j int) {
// 	arr[j], arr[i] = arr[i], arr[j]
// }

func doPerm(arr []int, pos int) {
	N := len(arr)
	if pos == N {
		displayPerm(arr)
		return
	}
	for i := pos; i < N; i++ {
		// swapElemInArr(&arr, i, pos)
		arr[i], arr[pos] = arr[pos], arr[i]
		doPerm(arr, pos+1)
		// swapElemInArr(&arr, i, pos)
		arr[i], arr[pos] = arr[pos], arr[i]
	}
}

func Perm(arr []int) {
	doPerm(arr, 0)
}

func main() {
	arr0 := [...]int{1, 2, 3, 4, 5}
	arr := arr0[:]
	Perm(arr)
}
