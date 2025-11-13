from figure import Figure
from color import Color
import math

class Circle(Figure):
    def __init__(self, radius: float, color: Color):
        super().__init__(color)
        self.radius = radius

    def area(self):
        return math.pi * self.radius ** 2

    def perimeter(self):
        return 2 * math.pi * self.radius

    def __str__(self):
        return f"{super().__str__()} - Radius: {self.radius}, Area: {self.area():.2f}, Circumference: {self.perimeter():.2f}"
