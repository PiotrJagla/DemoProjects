var MyNode = /** @class */ (function () {
    function MyNode(left, right, value) {
        this.right = right;
        this.left = left;
        this.value = value;
    }
    return MyNode;
}());
var traverse = function (root) {
    if (root === null) {
        return;
    }
    traverse(root.left);
    console.log(root.value);
    traverse(root.right);
};
var invert = function (root) {
    if (root === null) {
        return;
    }
    var temp = root.left;
    root.left = root.right;
    root.right = temp;
    invert(root.left);
    invert(root.right);
};
var groot = new MyNode(new MyNode(null, null, 1), new MyNode(new MyNode(null, null, 3), new MyNode(null, null, 5), 4), 2);
console.log("traversing before invert");
traverse(groot);
console.log("after invert");
invert(groot);
traverse(groot);
