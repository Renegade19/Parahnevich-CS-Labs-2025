from rectangle import Rectangle

class Square(Rectangle):
    def __init__(self, side: float, color):
        super().__init__(side, side, color)

    def __str__(self):
        return f"Квадрат со стороной {self.width}, цвет {self.color}"
