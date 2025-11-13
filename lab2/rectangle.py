from figure import Figure

class Rectangle(Figure):
    def __init__(self, width: float, height: float, color):
        super().__init__(color)
        self.width = width
        self.height = height

    def area(self):
        return self.width * self.height

    def perimeter(self):
        return 2 * (self.width + self.height)

    def __str__(self):
        return f"Прямоугольник {self.width}x{self.height}, цвет {self.color}"
