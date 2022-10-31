const xInput = document.querySelector("#graph-hidden-form\\:x-hidden-value")
const yInput = document.querySelector("#graph-hidden-form\\:y-hidden-value")
const rInput = document.querySelector("#graph-hidden-form\\:r-hidden-value")
const rSlider = document.querySelector("#graphForm\\:rValue")
const sendButton = document.querySelector("#graph-hidden-form\\:hidden-button")
const graph = document.querySelector('.graph')

function redrawGraphLabels(data) {
    if (data.status === "success") {
        changeGraphLabels()
    }
}

function changeGraphLabels(event, ui) {
    let rWholeNeg = document.querySelectorAll('.r-whole-neg')
    let rHalfNeg = document.querySelectorAll('.r-half-neg')
    let rHalfPos = document.querySelectorAll('.r-half-pos')
    let rWholePos = document.querySelectorAll('.r-whole-pos')
    let r
    if (ui) {
        r = ui.value
    } else {
        r = rSlider.value
    }
    rWholeNeg.forEach(el => {
        el.textContent = -r ? -r : "-R"
    })
    rHalfNeg.forEach(el => {
        el.textContent = -(r / 2) ? -(r / 2) : "-R/2"
    })
    rHalfPos.forEach(el => {
        el.textContent = +r / 2 ? r / 2 : "R/2"
    })
    rWholePos.forEach(el => {
        el.textContent = +r ? +r: "R"
    })
}

graph.addEventListener('click', event => {
    let x = (event.offsetX / 250 * 300).toString()
    let y = event.offsetY / 250 * 300
    let r = rSlider.value
    let xCord = Math.round((x / 300 - 0.5) * r * 3)
    let yCord = ((-y / 300) + 0.5) * 3 * r
    if (xCord >= -4 && xCord <= 4 && yCord <= 5 && yCord >= -3) {
        xInput.value = xCord
        yInput.value = Math.round(yCord * 10000) / 10000
        rInput.value = r
        sendButton.click()
    }
})

graph.addEventListener('mousemove', event => {
    let yDashLine = document.querySelector('.graph-y-dash-line')
    let xDashLine = document.querySelector('.graph-x-dash-line')

    let x = (event.offsetX / 250 * 300).toString()
    let y = event.offsetY / 250 * 300
    let r = rSlider.value
    let yCord = ((-y / 300) + 0.5) * 3 * r
    if (!r || yCord <= 5 && yCord >= -3) {
        yDashLine.setAttribute("y1", y)
        yDashLine.setAttribute("y2", y)
    }
    let xCord = Math.round((x / 300 - 0.5) * r * 3)
    if (r && xCord >= -4 && xCord <= 4) {
        let xGraphCord = (xCord / r / 3 + 0.5) * 300
        xDashLine.setAttribute("x1", xGraphCord)
        xDashLine.setAttribute("x2", xGraphCord)
    } else if (!r) {
        xDashLine.setAttribute("x1", x)
        xDashLine.setAttribute("x2", x)
    }
})

graph.addEventListener('mouseleave', event => {
    let yDashLine = document.querySelector('.graph-y-dash-line')
    let xDashLine = document.querySelector('.graph-x-dash-line')
    xDashLine.setAttribute("x1", -10)
    xDashLine.setAttribute("x2", -10)
    yDashLine.setAttribute("y1", -10)
    yDashLine.setAttribute("y2", -10)
})