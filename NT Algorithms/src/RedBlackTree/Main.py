import RedBlackTree
import Insertion
import Utils
import Deletion

def main():
    tree = RedBlackTree.RedBlackTree()
    root = tree.root
    node_value_list = []
    number_of_nodes = int(input("Enter the number of nodes you wish to add to the tree: "))
    for node in range(1,number_of_nodes + 1):
        value = int(input(f"Enter your {node} node value: "))
        node_value_list.append(value)
        Insertion.insert_the_node(tree, value)

    print('After insertion')
    Utils.print_the_tree(tree.root, "", True, True)

    node_to_delete = int(input("Enter the value of the node you wish to delete: "))

    if(node_to_delete in node_value_list):
        Deletion.delete_the_node(tree, tree.root ,node_to_delete)
        print(f"After deleting the node {node_to_delete} \n")
        Utils.print_the_tree(tree.root, "", True, True)
    else:
        print('Node is not present in the tree')
 

main()