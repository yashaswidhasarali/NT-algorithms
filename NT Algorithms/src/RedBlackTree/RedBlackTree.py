import TreeNode

class RedBlackTree():
    def __init__(self):
        self.null_node = TreeNode.TreeNode ( 0 )
        self.null_node.color_is_red = False
        self.null_node.left = None
        self.null_node.right = None
        self.root = self.null_node
