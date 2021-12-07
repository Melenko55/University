#include "pch.h"
#include "CppUnitTest.h"
#include "../EndedLab1/Matrix.h"
using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTestsMatrix
{
	TEST_CLASS(UnitTestsMatrix)
	{
	public:
		
		TEST_METHOD(TestMethod1)
		{
			vector<vector<double>> A1{ {3, 4}, {5, 7} };
			vector<vector<double>> B1{ {7, -4}, {-5, 3} };
			Matrix A(2, 2, A1);
			Matrix B(2, 2, B1);
			
			Assert::IsTrue(A== A);
		}
	};
}
