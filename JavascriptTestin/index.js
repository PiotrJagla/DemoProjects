
let input = "2*(6+4)*5"
let pos = 0
let peek = input.charAt(pos++)

function next() {
    let c = peek;
    peek = input.charAt(pos++);
    return c;
}

function split(delimiter, callback) {
    while(true) {
        callback();
        if(peek === delimiter){ 
            next();
        } else {
            break;
        }
    }
}

function number() {
    let isDigit = () => '0' <= peek && peek <= '9'
    let n = Number(next())
    while(isDigit()) {
        console.log(n);
        n = n * 10 + Number(next())
    }
    return n;
}

function expr() {
    let res = 0
    split('+', () => {
        let prod = 1
        split('*', () => {
            if(peek === '(') {
                next()
                prod *= expr()
                next()
            }
            else {
                prod *= number()
            }
        })
        res += prod
    })
    return res;
}

console.log(expr());

