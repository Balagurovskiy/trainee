
let selected=[];
function from_th_to_hidden(row, target){
    let th = row.getElementsByTagName("th");
    if(th.length > 0){
		
        let tdData = th[0].innerHTML;

		document.getElementById(target).value = tdData;
        
        if(selected.includes(row) === false){
            selected.push(row);
        }
        for(let s of selected){
            s.classList.remove("bg-warning");
        }
        row.classList.add("bg-warning");
    }
}