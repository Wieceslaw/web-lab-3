const canvas = document.querySelector('#clock-canvas')
const ctx = canvas.getContext("2d")
const width = canvas.width
const height = canvas.height

function tick() {
    ctx.clearRect(0, 0, canvas.width, canvas.height)
    // draw circle
    ctx.beginPath()
    ctx.arc(width / 2, height / 2, 50, 0, Math.PI * 2, true)
    ctx.stroke()
    ctx.save()
    ctx.fillStyle = "white"
    ctx.fill()
    ctx.restore()

    // draw point inside circle
    ctx.beginPath()
    ctx.arc(width / 2, height / 2, 2, 0, Math.PI * 2, true)
    ctx.save()
    ctx.fillStyle = "black"
    ctx.fill()
    ctx.restore()

    const currentDateTime = new Date();
    // draw hours line
    ctx.beginPath()
    const hoursR = 20
    const hours = currentDateTime.getHours()
    const hoursAngle = ((hours * 30 - 90) % 360) * Math.PI / 180
    let hxD = Math.cos(hoursAngle) * hoursR
    let hyD = Math.sin(hoursAngle) * hoursR
    ctx.moveTo(width / 2, height / 2)
    ctx.lineTo(width / 2 + hxD, height / 2 + hyD)
    ctx.stroke()

    // draw minutes line
    ctx.beginPath()
    const minutesR = 50
    const minutes = currentDateTime.getMinutes()
    const minutesAngle = ((minutes * 6 - 90) % 360) * Math.PI / 180
    const mxD = Math.cos(minutesAngle) * minutesR
    const myD = Math.sin(minutesAngle) * minutesR
    ctx.moveTo(width / 2, height / 2)
    ctx.lineTo(width / 2 + mxD, height / 2 + myD)
    ctx.stroke()

    // draw hours checks
    for (let i = 0; i < 360; i += 30) {
        ctx.beginPath()
        let angle = i * Math.PI / 180
        let xD = Math.cos(angle)
        let yD = Math.sin(angle)
        let r1 = 40
        let r2 = 50
        ctx.moveTo(width / 2 + xD * r1, height / 2 + yD * r1)
        ctx.lineTo(width / 2 + xD * r2, height / 2 + yD * r2)
        ctx.stroke()
    }

    // draw minutes checks
    for (let i = 0; i < 360; i += 6) {
        ctx.beginPath()
        let angle = i * Math.PI / 180
        let xD = Math.cos(angle)
        let yD = Math.sin(angle)
        let r1 = 45
        let r2 = 50
        ctx.moveTo(width / 2 + xD * r1, height / 2 + yD * r1)
        ctx.lineTo(width / 2 + xD * r2, height / 2 + yD * r2)
        ctx.stroke()
    }
}

setInterval(tick, 11 * 1000)
tick()
