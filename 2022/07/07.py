from typing import Union

with open('input.txt', 'r') as file:
    lines = file.read().splitlines()

MAX_SPACE = 70000000
MIN_SPACE = 30000000

class Directory():
    def __init__(self, name: str, parent: Union['Directory', None]) -> None:
        self.name: str = name
        self.children: list[Directory] = []
        self.files: dict[str, int] = {}
        self.parent: Directory | None = parent
        self.size:int = 0

    def get_root(self) -> 'Directory':
        return self if not self.parent else self.parent.get_root()

    def print_subtree(self, i: int) -> None:
        if not self.children:
            print('-'*i+f'{self.name}', self.size, self.files)
        else:
            print('-'*i+f'{self.name}', self.size, self.files)
            for child in self.children:
                child.print_subtree(i+1)

    def sub_tree_size(self, large:list['Directory']) -> int:
        if not self.children:
            self.size = sum(self.files.values())
            if self.size <= 100000:
                large.append(self)
            return self.size
        else:
            self.size = sum(self.files.values())+sum(child.sub_tree_size(large) for child in self.children)
            if self.size <= 100000:
                large.append(self)
            return self.size

    def delete(self, delete:list['Directory'], usage:int) -> None:
        potential = usage - self.size + MIN_SPACE
        if not self.children:
            if potential <= MAX_SPACE:
                delete.append(self)
        else:
            if potential <= MAX_SPACE:
                delete.append(self)
            for node in self.children:
                node.delete(delete,usage)

    def insert_and_cd(self, line: str, files: dict[str, int]) -> 'Directory':
        self.files = files.copy() if not self.files else self.files
        files.clear()
        if line.split()[-1] != '..':
            child = Directory(line.split()[-1], self)
            self.children.append(child)
            return child
        else:
            return self


curr_node: Directory | None = None
files: dict[str, int] = {}
for line in lines:
    if line.startswith('$ cd'):
        if not line[-2:] == '..':
            curr_node = Directory(
                line.split()[-1], None) if not curr_node else curr_node.insert_and_cd(line, files)
        elif curr_node and curr_node.parent:
            curr_node = curr_node.insert_and_cd(
                line, files).parent if not curr_node.files else curr_node.parent
    elif line.split()[0].isnumeric():
        files.update({line.split()[1]: int(line.split()[0])})
if curr_node:
    curr_node.insert_and_cd('cd ..', files)  # Insert files for last dir

large_dirs: list[Directory] = []
deletion_candidates: list[Directory] = []
root = curr_node.get_root() if curr_node else None
if root:
    root.sub_tree_size(large_dirs)
    root.delete(deletion_candidates,root.size)
    print(sum([dir.size for dir in large_dirs]))
    print(min([node.size for node in deletion_candidates]))

    
