function addTwo() {
    var _loop_1 = function (i) {
        setTimeout(function () {
            console.log(i);
        }, 100);
    };
    for (var i = 0; i < 3; ++i) {
        _loop_1(i);
    }
}
;
addTwo();
console.log(addTwo());
