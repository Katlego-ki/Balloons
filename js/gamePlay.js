//Use React!! Because it's easy

let container = document.querySelector('.container');

//To select mode easy:id = 1, medium: id = 2, hard: id = 3
let selectMode = () => {
    
    let chooseMode = document.createElement('div');

    for(let i = 1; i <= 3; i++){
        container.appendChild(chooseMode);
        chooseMode.id = i;
        chooseMode.setAttribute('onclick','balloonsFiled(this.id)' )
    }
}


//To Paint gray Balloons

let balloonsField = (mode) => {

    

}


/*
const newImg = document.createElement("img");
    newImg.classList.add('cards'); //??
    newImg.id = i;
    newImg.src = "images/Card-Pictures/Unturned.png";
    cardsGrid.appendChild(newImg);