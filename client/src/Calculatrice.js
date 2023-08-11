import { useCallback, useEffect, useMemo, useState } from "react"
import { Button } from "./components/Button"
import { Screen } from "./components/Screen"
import { compute, getEquation, getEquations } from "./services/computationService"
import { EquationList } from "./components/EquationList"

export const Calculatrice = () => {

    const [equation, setEquation] = useState({uid: null, equation: ""})
    const [equations, setEquations] = useState([])

    useEffect(() => {
        getEquations().then(data => setEquations(data))
    }, [])

    useEffect(() => {
        getEquations().then(data => setEquations(data))
    }, [equation])

    const onButtonPressed = (key) => async () => {
        const data = await compute(equation.uid, key);
        setEquation(data);
    }

    const onEquationSelected = useCallback((id) => {
        const eq = equations.filter(({ uid }) => uid === id)[0]
        setEquation(eq)
    }, [equations])

    const onNewClicked = async () => {
        const data  = await compute(crypto.randomUUID(), "New");
        setEquation(data)
    }
    
    const onSaveClicked = async () => {
        await compute(equation.uid, "Save");
        setEquation({ uid: null, equation: "" })
    }

    const disabledCompute = useMemo(() => {
        return equation.uid === null
    }, [equation])

    return <div style={{width: 400}}>
        <EquationList data={equations} onSelect={onEquationSelected} />

        <Screen equation={equation} />

        <div className="flex-center">
            <Button disabled={disabledCompute} onClick={onButtonPressed("Clear")} style={{ width: "20%" }} label="Clear"/>
            <Button onClick={onNewClicked} style={{ width: "20%" }} label="New"/>
            <Button disabled={disabledCompute} onClick={onSaveClicked} style={{ width: "20%" }} label="Save"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed("/")} style={{ width: "20%" }} label="/"/>
        </div>

        <div className="flex-center">
            <Button disabled={disabledCompute} onClick={onButtonPressed(7)} style={{ width: "20%" }} label="7"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed(8)} style={{ width: "20%" }} label="8"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed(9)} style={{ width: "20%" }} label="9"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed("*")} style={{ width: "20%" }} label="*"/>
        </div>

        <div className="flex-center">
            <Button disabled={disabledCompute} onClick={onButtonPressed(4)} style={{ width: "20%" }} label="4"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed(5)} style={{ width: "20%" }} label="5"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed(6)} style={{ width: "20%" }} label="6"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed("-")} style={{ width: "20%" }} label="-"/>
        </div>
        
        <div className="flex-center">
            <Button disabled={disabledCompute} onClick={onButtonPressed(1)} style={{ width: "20%" }} label="1" />
            <Button disabled={disabledCompute} onClick={onButtonPressed(2)} style={{ width: "20%" }} label="2"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed(3)} style={{ width: "20%" }} label="3"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed("+")} style={{ width: "20%" }} label="+"/>
        </div>

        <div className="flex-center">
            <Button disabled={disabledCompute} onClick={onButtonPressed("%")} style={{ width: "20%" }} label="%"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed(0)} style={{ width: "20%" }} label="0"/>
            <Button disabled={disabledCompute} onClick={onButtonPressed(".")} style={{ width: "20%" }} label="."/>
            <Button disabled={disabledCompute} onClick={onButtonPressed("Enter")} style={{ width: "20%" }} label="Enter"/>
        </div>
    </div>
}