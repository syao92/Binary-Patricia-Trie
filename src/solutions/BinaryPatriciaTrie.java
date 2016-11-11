package solutions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryPatriciaTrie {
	
	Node root = null;
	
	public class Node {
		public int index;
		public Node left;
		public Node right;
		public String key;
		
		public Node() {
			index = 0;
			left = null;
			right = null;
			key = null;
		}
		
		public Node(String s, int i) {
			index = i;
			left = null;
			right = null;
			key = s;
		}
	}
	
	public boolean search(String s) {
		if (s == null || root == null) {
			return false;
		}
		return search(s, root);
	}
	
	public boolean search(String s, Node travel) {
		if (travel == null) {
			return false;
		}

		if (travel.key == null) {
			if (travel.index >= s.length()) {
				return false;
			} else if (s.charAt(travel.index) == '0') {
				return search(s, travel.left);
			} else {
				return search(s, travel.right);
			}
		} else {
			if (s.equals(travel.key) && s.length() == travel.key.length()) {
				return true;
			} else {
				int length = Math.min(travel.key.length(), s.length());
				int diff_index = -1;
				
				for (int i = 0; i < length; i++) {
					if (travel.key.charAt(i) != s.charAt(i)) {
						diff_index = i;
						break;
					}
				}
				if (diff_index == -1) {
					if (s.length() >= travel.key.length()) {
						if (s.charAt(length) == '0') {
							return search(s, travel.left);
						} else {
							return search(s, travel.right);
						}
					} else {
						return false;
					}
				} else {
					if (s.charAt(diff_index) == '0') {
						return search(s, travel.left);
					} else {
						return search(s, travel.right);
					}
				}
			}
		}
		
		
	}
	
	public boolean insert(String s) {
		// Check if current trie is empty (Base Case)
		if (root == null) {
			root = new Node(s, s.length());
			return true;
			
		// Check if current trie already contains the key
		} else if (search(s) == true) {
			return false;
		}
		
		Node travel = root;
		return insert(s, travel, null, 0, -1);
	}
	
	public boolean insert(String s, Node travel, Node prev, int direction, int index_level) {
		// Reached null meaning, it is the longest current leaf.
		if (travel == null) {
			Node newNode = new Node(s, s.length());
			if (direction == 0) {
				prev.left = newNode;
			} else {
				prev.right = newNode;
			}
			
		// No Key, figure out which way to traverse
		} else if (travel.key == null) {
			// s length same as index
			if (travel.index == s.length()) {
				int diff_index = -1;
				Node node_with_string = travel;
				
				while (node_with_string.key == null) {
					node_with_string = node_with_string.left;
				}
				
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) != node_with_string.key.charAt(i)) {
						diff_index = i;
						break;
					}
				}
				
				Node newNode = new Node(s, s.length());
				Node diff_node = new Node(null, diff_index);
				
				// Fill in prefix into null key node
				if (diff_index == -1) {
					travel.key = s;
					travel.index = s.length();
				} else {
					// Insertion into middle of tree
					if (prev != null) {
						if (direction == 0) {
							prev.left = diff_node;
						} else {
							prev.right = diff_node;
						}
						if (s.charAt(diff_index) == '0') {
							diff_node.left = newNode;
							diff_node.right = travel;
						} else {
							diff_node.left = travel;
							diff_node.right = newNode;
						}
					
					// Has to be root case
					} else {
						if (s.charAt(diff_index) == '0') {
							diff_node.left = newNode;
							diff_node.right = travel;
						} else {
							diff_node.left = travel;
							diff_node.right = newNode;
						}
						root = diff_node;
					}
				}
			// index is less than s length
			} else if (travel.index < s.length()) {
				int diff_index = -1;
				Node node_with_string = travel;
				
				while (node_with_string.key == null) {
					node_with_string = node_with_string.left;
				}
				
				for (int i = 0; i < travel.index; i++) {
					if (s.charAt(i) != node_with_string.key.charAt(i)) {
						diff_index = i;
						break;
					}
				}
				
				if (diff_index == -1) {
					if (s.charAt(travel.index) == '0') {
						return insert(s, travel.left, travel, 0, travel.index);
					} else {
						return insert(s, travel.right, travel, 1, travel.index);
					}
				} else {
					Node newNode = new Node(s, s.length());
					Node diff_node = new Node(null, diff_index);
					
					if (diff_index < travel.index) {
						if (prev != null) {
							if (s.charAt(diff_index) == '0') {
								diff_node.left = newNode;
								diff_node.right = travel;
							} else {
								diff_node.left = travel;
								diff_node.right = newNode;
							}
							if (direction == 0) {
								prev.left = diff_node;
							} else {
								prev.right = diff_node;
							}
						} else {
							if (s.charAt(diff_index) == '0') {
								diff_node.left = newNode;
								diff_node.right = travel;
							} else {
								diff_node.left = travel;
								diff_node.right = newNode;
							}
							root = diff_node;
						}
					}
				}
			} else {
				int diff_index = -1;
				Node node_with_string = travel;
				
				while (node_with_string.key == null) {
					node_with_string = node_with_string.left;	
				}
				
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) != node_with_string.key.charAt(i)) {
						diff_index = i;
						break;
					}
				}
				
				Node newNode = new Node(s, s.length());
				
				if (diff_index != -1) {
					Node diff_node = new Node(null, diff_index);
					
					if (prev == null) {
						if (s.charAt(diff_index) == '0') {
							diff_node.left = newNode;
							diff_node.right = travel;
						} else {
							diff_node.left = travel;
							diff_node.right = newNode;
						}
						root = diff_node;
					} else {
						if (s.charAt(diff_index) == '0') {
							diff_node.left = newNode;
							diff_node.right = travel;
						} else {
							diff_node.left = travel;
							diff_node.right = newNode;
						}
						
						if (direction == 0) {
							prev.left = diff_node;
						} else {
							prev.right = diff_node;
						}
					}
				} else {
					if (prev != null) {
						if (direction == 0) {
							prev.left = newNode;
						} else {
							prev.right = newNode;	
						}
					
						if (node_with_string.key.charAt(s.length()) == '0') {
							newNode.left = travel;
						} else {
							newNode.right = travel;
						}
					} else {
						if (prev == null) {
							if (node_with_string.key.charAt(s.length()) == '0') {
								newNode.left = travel;
							} else {
								newNode.right = travel;
							}
							root = newNode;
						}
					}
				}
			}
		
		// Finds a key
		} else {
			int length = Math.min(s.length(), travel.key.length());
			int diff_index = -1;
			
			// Find the index where the two strings differ
			for (int i = 0; i < length; i++) {
				if (s.charAt(i) != travel.key.charAt(i)) {
					diff_index = i;
					break;
				}
			}
			// Two strings do differ
			if (diff_index != -1) {
				Node diff_node = new Node(null, diff_index);
				Node newNode = new Node(s, s.length());
				
				if (s.charAt(diff_index) == '0') {
					diff_node.left = newNode;
					diff_node.right = travel;
					if (prev != null) {
						if (direction == 0) {
							prev.left = diff_node;
						} else {
							prev.right = diff_node;
						}
					}
					if (index_level == -1) {
						root = diff_node;
					}
				} else {
					diff_node.left = travel;
					diff_node.right = newNode;
					if (prev != null) {
						if (direction == 0) {
							prev.left = diff_node;
						} else {
							prev.right = diff_node;
						}
					}
					if (index_level == -1) {
						root = diff_node;
					}
				}
			// One string is longer and contains the whole string of the other
			} else {
				Node newNode = new Node(s, s.length());
				
				if (s.length() > travel.key.length()) { 
					if (s.charAt(travel.key.length()) == '0') {
						return insert(s, travel.left, travel, 0, travel.index);
					} else {
						return insert(s, travel.right, travel, 1, travel.index);
					}
					
				} else {
					if (travel.key.charAt(s.length()) == '0') {
						newNode.left = travel;
						
						if (prev != null) {
							if (direction == 0) {
								prev.left = newNode;
							} else {
								prev.right = newNode;
							}
						} else {
							root = newNode;
						}
					} else {
						newNode.right = travel;
						
						if (prev != null) {
							if (direction == 0) {
								prev.left = newNode;
							} else {
								prev.right = newNode;
							}
						} else {
							root = newNode;
						}
					}
				}
				
			}
		}
		return true;	
	}
	
	public boolean delete (String s) {
		if (s == null || root == null || search(s) == false) {
			return false;
		}
		
		return delete(s, root, null, 0, -1);
	}
	
	public boolean delete(String s, Node travel, Node prev, int direction, int index_level) {
		if (travel.key == null) {
			if (s.charAt(travel.index) == '0') {
				return delete(s, travel.left, travel, 0, travel.index);
			} else {
				return delete(s, travel.right, travel, 1, travel.index);
			}
		} else {
			if (travel.key.equals(s)) {
				if (prev == null) {
					if (root.left == null && root.right == null) {
						root = null;
					} else if (root.left != null && root.right != null) {
						root.key = null;
					} else if (root.left == null) {
						root = root.right;
					} else {
						root = root.left;
					}
				} else {
					if (travel.left != null && travel.right != null) {
						travel.key = null;
					} else if (travel.left == null && travel.right == null) {
						if (prev.key != null) {
							if (direction == 0) {
								prev.left = null;
							} else {
								prev.right = null;
							}
						} else {
							if (prev != root) {
								if (direction == 0) {
									prev.index = prev.right.index;
									prev.key = prev.right.key;
									prev.left = prev.right.left;
									prev.right = prev.right.right;
								} else {
									prev.index = prev.left.index;
									prev.key = prev.left.key;
									prev.right = prev.left.right;
									prev.left = prev.left.left;
								}
							} else {
								if (direction == 0) {
									root = prev.right;
								} else {
									root = prev.left;
								}
							}
						}
					} else {
						if (travel.left != null) {
							if (direction == 0) {
								prev.left = travel.left;
							} else {
								prev.right = travel.left;
							}
						} else {
							if (direction == 0) {
								prev.left = travel.right;
							} else {
								prev.right = travel.right;
							}
						}
					}
				}
				
			} else {
				if (s.charAt(travel.index) == '0') {
					return delete(s, travel.left, travel, 0, travel.index);
				} else {
					return delete(s, travel.right, travel, 1, travel.index);
				}
			}
		}
		return true;
	}
	
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}
	
	public int getSize() {
		return getSize(root);
	}
	
	public int getSize(Node root) {
		if (root == null) {
			return 0;
		}
		if (root.key != null) {
			return 1 + getSize(root.left) + getSize(root.right);
		} else {
			return 0 + getSize(root.left) + getSize(root.right);
		}
	}
	
	public class TrieIterator implements Iterator<String>  {
		private ArrayList<String> list;
		private int index;
		
		public TrieIterator() {
			list = new ArrayList<String>();
			addToList(root);
			index = 0;
		}

		public void addToList(Node root) {
			if (root == null) {
				return;
			}
			addToList(root.left);
			if (root.key != null) {
				list.add(root.key);
			}
			addToList(root.right);
		}
		
		@Override
		public boolean hasNext() {
			if (index < list.size()) {
				return true;
			}
			return false;
		}

		@Override
		public String next() {
			if (hasNext()) {
				return list.get(index++);
			} else {
				throw new NoSuchElementException();
			}
		}
		
	}
	
	public Iterator<String> inOrderTraversal() {
		return new TrieIterator();
	}
	
	
	public String getLongest() {
		if (root == null) {
			return null;
		}
		return getLongest(root);
	}
	
	public String getLongest(Node root) {
		if (root == null) {
			return "";
		} else if (root.left == null && root.right == null) {
			return root.key;
		} 
		
		String left = getLongest(root.left);
		String right = getLongest(root.right);
		
		if (left.length() > right.length()) {
//			System.out.println(left);
			return left;
		} else {
//			System.out.println(right);
			return right;
		}
			
	}
	
	public void printTree(Node root) {
		if (root == null) {
			return;
		}
		printTree(root.left);
		
		if (root != null) {
			System.out.println(root.key);
		}
		
		printTree(root.right);
	}



}
