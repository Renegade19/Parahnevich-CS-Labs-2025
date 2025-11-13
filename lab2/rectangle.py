from figure import Figure
from color import Color

class Rectangle(Figure):
    def __init__(self, width: float, height: float, color: Color):
        super().__init__(color)
        self.width = width
        self.height = height

    def area(self):
        return self.width * self.height

    def perimeter(self):
        return 2 * (self.width + self.height)

    def __str__(self):
        return f"{super().__str__()} - Width: {self.width}, Height: {self.height}, Area: {self.area()}, Perimeter: {self.perimeter()}"
