from abc import ABC, abstractmethod
from color import Color

class Figure(ABC):
    def __init__(self, color: Color):
        self.color = color

    @abstractmethod
    def area(self):
        pass

    @abstractmethod
    def perimeter(self):
        pass

    def __str__(self):
        return f"{self.__class__.__name__} цвета {self.color}"
