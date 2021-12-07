#include "pch.h"

TEST(TestCaseName, TestName) {
  EXPECT_EQ(1, 1);
  EXPECT_TRUE(true);
}

//LU And Gaus
vector<vector <double>> a = { {1, 2}, {1, 1} };
Matrix A(2, 2, a);
vector<vector <double>> a1 = { {-1, 2}, {1, -1} };
Matrix A1(2, 2, a1);
vector<vector <double>> b = { {3, 4}, {5, 7} };
Matrix B(2, 2, b);
vector<vector <double>> b1 = { {7, -4}, {-5, 3} };
Matrix B1(2, 2, b1);
vector<vector <double>> c = { {2, 5, 7}, {6, 3, 4}, {5, -2, -3} };
Matrix C(3, 3, c);
vector<vector <double>> c1 = { {1, -1, 1}, {-38, 41, -34}, {27, -29, 24} };
Matrix C1(3, 3, c1);
vector<vector <double>> d = { {3, -4, 5}, {2, -3, 1}, {3, -5, -1} };
Matrix D(3, 3, d);
vector<vector <double>> d1 = { {-8, 29, -11}, {-5, 18, -7}, {1, -3, 1} };
Matrix D1(3, 3, d1);
vector<vector <double>> e = { {1, 2, 3, 4}, {2, 3, 1, 2}, {1, 1, 1, -1} ,{1, 0, -2, -6} };
Matrix E(4, 4, e);
vector<vector <double>> e1 = { {22, -6, -26, 17}, {-17, 5, 20, -13}, {-1, 0, 2, -1}, {4, -1, -5, 3} };
Matrix E1(4, 4, e1);

//determinate 0
vector<vector <double>> f = { {4, 6} ,{6, 9} };
Matrix F(2, 2, f);
vector<vector <double>> g = { {3, 2, 1} ,{6, 4, 3}, {9, 6, 5} };
Matrix G(2, 2, g);
vector<vector <double>> q = { {2, -3, 5} ,{4, -6, 2}, {2, -3, -11} };
Matrix Q(2, 2, q);

vector<vector <double>> AMultiplyB = { {13, 18}, {8, 11} };
Matrix(2, 2, AMultiplyB);
vector<vector <double>> CMultiplyD = { {37, -60, 8}, {36, -59, 29}, {2, -4, 26} };
Matrix(3, 3, CMultiplyD);
vector<vector <double>> X = { {1, 2, -3}, {3, 3, -4}, {2, -1, 0} };
Matrix(3, 3, X);
vector<vector <double>> Y = { {6, 4, 5}, {2, 1, 2}, {3, 3, 3} };
Matrix(3, 3, Y);
vector<vector <double>> XMultiplyY = { {1, -3, 0}, {10, 2, 7}, {10, 7, 8} };
Matrix(3, 3, XMultiplyY);
