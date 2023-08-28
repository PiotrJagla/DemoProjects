function addTwo(): void {
    for(let i = 0 ; i < 3 ; ++i) {
        setTimeout(() => {
            console.log(i);
        }, 100);
    }
};


addTwo();
console.log(addTwo());