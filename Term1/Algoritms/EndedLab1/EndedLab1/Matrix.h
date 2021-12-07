#pragma once
#include <vector>

using namespace std;

class Matrix
{
private:
    int rows;
    int colums;
    vector<vector<double>> matrix;
public:
    Matrix(int row, int colum) : rows(row), colums(colum), matrix(row, vector<double>(colum)) {};
    Matrix(int row, int colum, vector<vector<double>> A) : rows(row), colums(colum), matrix(A) {};
    void entreMatrix();
    void print();
    void identity_matrix(Matrix A);
    void swap_row(int place_row);
    void GausseJordan(Matrix A);
    Matrix inverseByLU();
    double& operator() (int row, int col);
    Matrix& operator=(const Matrix& other);
    bool operator==(const Matrix& rmat);
    friend Matrix operator+(const Matrix& lmat, const Matrix& rmat);
    friend Matrix operator-(const Matrix& lmat, const Matrix& rmat);
    friend Matrix operator*(const Matrix& lmat, const Matrix& rmat);
    Matrix Get_part(int i);
    Matrix Insert_part(Matrix a, int part);
    vector<vector<double>> getMatrix();
};
