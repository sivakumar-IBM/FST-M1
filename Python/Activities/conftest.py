import pytest


@pytest.fixture
def input_value():
    input = 25
    return input
@pytest.fixture
def numberslist():
    input = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    return input
@pytest.fixture
def walletamount():
    amount = 0
    return amount
