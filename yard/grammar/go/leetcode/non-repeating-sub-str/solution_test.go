package main

import "testing"

func TestSubstr(t *testing.T) {
	tests := []struct {
		s      string
		answer int
	}{
		// Normal cases
		{"abcabcbb", 3},
		{"pwwkew", 3},

		// Edge cases
		{"", 0},
		{"b", 1},
		{"bbbbb", 1},
		{"abcabcabcd", 4},

		// Chinese support
		{"这里是慕课网", 6},
		{"一二三二一", 3},
		{"黑灰化肥灰会挥发发灰黑讳为黑灰花会飞", 7},
	}

	for _, tt := range tests {
		actual := lengthOfNonRepeatingSubStr(tt.s)
		if actual != tt.answer {
			t.Errorf("got %d for input %s; expected %d",
				actual, tt.s, tt.answer)
		}
	}
}

func BenchmarkSubStr(b *testing.B) {
	s := "黑灰化肥灰会挥发发灰黑讳为黑灰花会飞"
	for i := 0; i < 13; i++ {
		s += s
	}
	b.Logf("len(s) = %d", len(s))
	answer := 7
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		actual := lengthOfNonRepeatingSubStr(s)
		if actual != answer {
			b.Errorf("got %d for input %s; "+
				"expected %d",
				actual, s, answer)
		}
	}
}
