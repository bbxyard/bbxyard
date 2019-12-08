package main

import "testing"

type ErrorReporter interface {
	Errorf(format string, args ...interface{})
}

func doTestTriangle(reporter ErrorReporter) {
	tests := []struct{ a, b, c int }{
		{3, 4, 5},
		{5, 12, 13},
		{8, 15, 17},
		{12, 35, 37},
		{30000, 40000, 50000},
	}

	for _, tt := range tests {
		if actual := calcTriangle(tt.a, tt.b); actual != tt.c {
			reporter.Errorf("calcTriangle(%d, %d); "+
				"got %d; expected %d",
				tt.a, tt.b, actual, tt.c)
		}
	}
}

func TestTriangle(t *testing.T) {
	var errorReporter ErrorReporter = t
	doTestTriangle(errorReporter)
}

func BenchmarkTriangle(b *testing.B) {
	var errorReporter ErrorReporter = b
	for i := 0; i < b.N; i++ {
		doTestTriangle(errorReporter)
	}
}
