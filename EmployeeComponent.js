import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getEmployeeById, updateEmployee } from "./EmployeeService";
import { createEmployee } from "./EmployeeService";

const EmployeeComponent=()=>{
    const[firstName,setFirstName]=useState('')
    const[lastName,setLastName]=useState('')
    const[email,setEmail]=useState('')

    const navigate=useNavigate();
    const{id}=useParams();

    const saveOrUpdateEmployee=(e)=>{
        e.preventDefault();

        const employee={firstName,lastName,email};

        console.log(employee);

        if(id){
            updateEmployee(id,employee).then((response)=>{
                console.log(response.data);
                navigate('/employees')
            }).catch(error=>{
                console.log(error);
            })
        }else{
            createEmployee(employee).then((response)=>{
                console.log(response.data);
                navigate('/employees');
            }).catch(error=>{
                console.log(error);
            })
        }
    }

    useEffect(()=>{
        if(id){
            getEmployeeById(id).then((response)=>{
                setFirstName(response.data.firstName)
                setLastName(response.data.lastName)
                setEmail(response.data.email)
            }).catch(error=>{
                console.log(error);
            })
        }
    },[id])

    const pageTitle=()=>{
        if(id){
            return <h2 className="text-center">Update Employee</h2>
        }else{
            return <h2 className="text-center">Add Employee</h2>
        }

    }

    return(

        <div >
            <br/><br/>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        {
                            pageTitle()
                        }
                        <div className="card-body">
                            <form >
                                <div className="form-group mb-2">
                                    <label className="form-label">First Name</label>
                                    <input type="text"
                                    placeholder="Enter First Name"
                                    name="firstName"
                                    className="form-contol"
                                    value={firstName}
                                    onChange={(e)=>{
                                        setFirstName(e.target.value)
                                    }}
                                    
                                    >
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Last Name : </label>
                                    <input type="text"
                                    placeholder="Enter First Name"
                                    name="lastName"
                                    className="form-contol"
                                    value={lastName}
                                    onChange={(e)=>{
                                        setLastName(e.target.value)
                                    }}
                                    
                                    >
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Email : </label>
                                    <input type="email"
                                    placeholder="Enter Email"
                                    name="email"
                                    className="form-contol"
                                    value={email}
                                    onChange={(e)=>{
                                        setEmail(e.target.value)
                                    }}
                                    
                                    >
                                    </input>

                                </div>

                                <button className="btn btn-success" onClick={(e)=>saveOrUpdateEmployee(e)}>Submit</button>
                                {/*<link to="/employees" className="btn btn-danger">Cancel</link>*/}

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    )
}

export default EmployeeComponent