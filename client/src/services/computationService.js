let data = [
    {
        uid: 1,
        equation: "3 10 5+*",
    },
    {
        uid: 2,
        equation: "10 5+3*"
    }
]

const url = "http://localhost:8080/rpn"

export const getEquations = async () => {
    const response = await fetch(url + "/equations")
    const json = await response.json()
    return json
}

export const getEquation = (id) => {
    return data.filter(({ uid }) => uid === id)[0];
}

export const compute = async (id, key) => {
    const response = await fetch(url + "/equation/compute", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
        body: JSON.stringify({
            uid: id,
            equation: key
        })
    })

    const json = await response.json()
    return json;
}