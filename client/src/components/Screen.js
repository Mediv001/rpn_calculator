export const Screen = ({equation}) => {
    return <div style={{ border: "dashed black", backgroundColor: "antiquewhite", minHeight: 24, overflow: "auto" }}>
        {equation.equation}
    </div>
}