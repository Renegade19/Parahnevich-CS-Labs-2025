class Unique:
    def __init__(self, items, **kwargs):
        self._items = items
        self._ignore_case = kwargs.get('ignore_case', False)
        self._seen = set()
        self._iter = iter(items)

    def __iter__(self):
        return self

    def __next__(self):
        for item in self._iter:
            val = item
            if self._ignore_case and isinstance(item, str):
                key = item.lower()
            else:
                key = item
            if key not in self._seen:
                self._seen.add(key)
                return item
        raise StopIteration


if __name__ == '__main__':
    data1 = [1, 1, 1, 1, 1, 2, 2, 2, 2, 2]
    print(list(Unique(data1)))

    data2 = (x for x in [1,2,1,3,2,3])
    print(list(Unique(data2)))

    data3 = ['a','A','b','B','a','A','b','B']
    print(list(Unique(data3)))
    print(list(Unique(data3, ignore_case=True)))
