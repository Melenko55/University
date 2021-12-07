#include <gtest/gtest.h>
#include "Matrix.h"
#include <iostream>

using namespace std;

TEST(TestGaus, Test1) {
	vector<vector <double>> v = { {1, 2}, {3, 4} };
	vector<vector <double>> v1 = { {-2, 1}, {1.5, -0.5} };
	Matrix current(2,2, v);
	Matrix expected(2, 2, v1);
	ASSERT_TRUE(current == expected);
}

int main(int argc, char **argv) {
	RUN_ALL_TESTS();
}