import fetch from "node-fetch";
interface isThisName {
    name: string;
}


// const responseOfFetch = await fetch("http://localhost:4000/")
//     .then((res) => res.json())
//     .then((data) => console.log(data));

const getFirstName = async(): Promise<isThisName> => {
    return new Promise(async (resolve, reject) => {
        try{
            console.log("getting the name");
            const responseOfFetch = await fetch("http://localhost:4000/");
            resolve( await responseOfFetch.json());
        }
        catch(e) {
            // console.error(e);
            reject(e);
        }
    });
}

const getName = async(): Promise<isThisName> => {
    const responseOfFetch = await fetch("http://localhost:4000/");
    return await responseOfFetch.json();
}
(async function() {
    try{
        // // const responseOfFetch = await fetch("http://localhost:4000/");
        // // const jsonRes: any = await responseOfFetch.json();
        // console.log(await getName());
        // console.log("tutaj jest");

        const costam = getFirstName();
        // console.log(await costam);
        // console.log(await costam);
        // for(let i = 0 ; i < 16 ; ++i) {
        //     console.log(i);
        //     console.log(await costam);
        // }

        let lst: numer[] = [1,2,3,4,5,6,7,8,9,10,11,12,13,14];

        Promise.all(list.map(() => getName()));
    }
    catch(e) {
        console.error(e);
    }
})();